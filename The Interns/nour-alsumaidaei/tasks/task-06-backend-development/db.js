const Database = require("better-sqlite3");
const path = require("path");

const db = new Database(path.join(__dirname, "portfolio.db"));
db.pragma("journal_mode = WAL");

// ---------- Schema ----------
db.exec(`
  CREATE TABLE IF NOT EXISTS profile (
    id INTEGER PRIMARY KEY CHECK (id = 1),
    name TEXT NOT NULL,
    title TEXT NOT NULL,
    location TEXT NOT NULL,
    education TEXT NOT NULL,
    languages TEXT NOT NULL,   -- JSON array
    bio TEXT NOT NULL
  );

  CREATE TABLE IF NOT EXISTS skills (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    category TEXT NOT NULL,
    level INTEGER NOT NULL
  );

  CREATE TABLE IF NOT EXISTS experience (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    company TEXT NOT NULL,
    role TEXT NOT NULL,
    highlights TEXT NOT NULL   -- JSON array
  );

  CREATE TABLE IF NOT EXISTS projects (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    stack TEXT NOT NULL        -- JSON array
  );

  CREATE TABLE IF NOT EXISTS contact_messages (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    message TEXT NOT NULL,
    received_at TEXT NOT NULL
  );
`);

// ---------- Seed (only if empty) ----------
const seed = db.transaction(() => {
  const profileCount = db.prepare("SELECT COUNT(*) AS c FROM profile").get().c;
  if (profileCount === 0) {
    db.prepare(
      `INSERT INTO profile (id, name, title, location, education, languages, bio)
       VALUES (1, ?, ?, ?, ?, ?, ?)`
    ).run(
      "Nour",
      "Data Analyst",
      "Baghdad, Iraq",
      "B.Sc. Computer Science, American University of Iraq - Baghdad (AUIB)",
      JSON.stringify(["Arabic", "English"]),
      "Computer Science student and aspiring data analyst with hands-on experience building data pipelines, automated analytics scripts, and real-time monitoring dashboards."
    );
  }

  const skillCount = db.prepare("SELECT COUNT(*) AS c FROM skills").get().c;
  if (skillCount === 0) {
    const insertSkill = db.prepare(
      "INSERT INTO skills (name, category, level) VALUES (?, ?, ?)"
    );
    [
      ["Python", "Language", 90],
      ["SQL", "Language", 88],
      ["Pandas", "Library", 85],
      ["Grafana", "Tool", 80],
      ["Power BI", "Tool", 75],
      ["Excel", "Tool", 85],
    ].forEach((s) => insertSkill.run(...s));
  }

  const expCount = db.prepare("SELECT COUNT(*) AS c FROM experience").get().c;
  if (expCount === 0) {
    db.prepare(
      "INSERT INTO experience (company, role, highlights) VALUES (?, ?, ?)"
    ).run(
      "Qi Card Company",
      "Data / Backend Intern",
      JSON.stringify([
        "Built data pipelines for internal analytics",
        "Wrote automated analytics scripts using Python and Pandas",
        "Built real-time monitoring dashboards with Grafana",
        "Used SQL for data extraction and transformation",
      ])
    );
  }

  const projCount = db.prepare("SELECT COUNT(*) AS c FROM projects").get().c;
  if (projCount === 0) {
    const insertProj = db.prepare(
      "INSERT INTO projects (name, description, stack) VALUES (?, ?, ?)"
    );
    insertProj.run(
      "AI-Powered Real Estate Platform",
      "Senior project for the Iraqi real estate market with PropTech commercial ambitions, built with a partner.",
      JSON.stringify(["Next.js", "React Three Fiber", "FastAPI", "PostgreSQL", "AI APIs"])
    );
    insertProj.run(
      "Personal CV Website",
      "Personal CV website built with HTML, CSS, and JavaScript for a Web Programming course.",
      JSON.stringify(["HTML", "CSS", "JavaScript"])
    );
  }
});

seed();

module.exports = db;
