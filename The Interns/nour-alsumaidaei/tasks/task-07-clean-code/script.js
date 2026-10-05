"use strict";

/**
 * script.js
 * Behavior for the CV dashboard. Previously this lived as inline <script>
 * markup mixed with anonymous onclick handlers. It is now split out
 * (separation of concerns), broken into small named functions
 * (single responsibility), and uses guard clauses instead of nested
 * if/else (simplified logic).
 */

const BACKGROUND_COLORS = ["#1b2233", "#0f3d3e", "#3b2f4a", "#33261d"];

/** Shorthand for document.getElementById, used everywhere below to avoid
 *  repeating the same lookup pattern on every line (reduces duplication). */
function byId(id) {
    return document.getElementById(id);
}

/**
 * Counts how many times `targetWord` appears in `text` as a whole word.
 * Pure function: does not read or write the DOM, and does not mutate
 * its parameters, so it is easy to test on its own.
 */
function countWordOccurrences(text, targetWord) {
    const normalizedText = text.toLowerCase().replace(/[.,]/g, "");
    const normalizedTarget = targetWord.toLowerCase();
    const words = normalizedText.split(/\s+/);
    return words.filter((word) => word === normalizedTarget).length;
}

/**
 * Returns a click handler that cycles the page background through
 * `colors`, one step at a time, wrapping back to the start.
 * The index lives in a closure instead of a global variable, so nothing
 * else on the page can accidentally reset or corrupt it.
 */
function createBackgroundCycler(colors) {
    let index = 0;
    return function cycleBackground() {
        index = (index + 1) % colors.length;
        document.body.style.backgroundColor = colors[index];
    };
}

function handleGreetButtonClick() {
    const visitorName = prompt("What is your name?");
    if (!visitorName) return;

    byId("greeting").innerText = `Welcome, ${visitorName}! Thanks for visiting my CV.`;
    document.title = `Welcome ${visitorName} - Nour's CV`;
}

function handleToggleProjectsClick(event) {
    const projectsPanel = byId("projects");
    const isHidden = projectsPanel.style.display === "none";

    projectsPanel.style.display = isHidden ? "block" : "none";
    event.target.innerText = isHidden ? "Hide Projects" : "Show Projects";
}

function handleSearchButtonClick() {
    const word = prompt("Enter a word to search for:");
    if (!word) return;

    const summaryText = byId("summary").innerText;
    const occurrences = countWordOccurrences(summaryText, word);
    byId("search-result").innerText =
        `The word '${word}' appears ${occurrences} times in my summary.`;
}

function handleAddSkillButtonClick() {
    const newSkill = prompt("Enter new skill:");
    if (!newSkill) return;

    const skillItem = document.createElement("li");
    skillItem.className = "skill-item";
    skillItem.textContent = newSkill;
    byId("skill-list").appendChild(skillItem);
}

function handleDeleteSkillButtonClick() {
    const skillItems = document.getElementsByClassName("skill-item");
    if (skillItems.length === 0) {
        alert("No skills to delete.");
        return;
    }
    byId("skill-list").removeChild(skillItems[skillItems.length - 1]);
}

function handleSendButtonClick() {
    const visitorName = byId("visitor-name").value;
    if (!visitorName) {
        alert("Please write your name first.");
        return;
    }
    alert(`Thank you ${visitorName}! Your message was received.`);
}

/** Wires every button to its handler. Kept separate from the handler
 *  definitions above so "what each action does" and "when it runs" can
 *  be read and changed independently. */
function initCvDashboard() {
    const cycleBackground = createBackgroundCycler(BACKGROUND_COLORS);

    byId("greet-button").addEventListener("click", handleGreetButtonClick);
    byId("color-button").addEventListener("click", cycleBackground);
    byId("toggle-button").addEventListener("click", handleToggleProjectsClick);
    byId("search-button").addEventListener("click", handleSearchButtonClick);
    byId("add-skill-button").addEventListener("click", handleAddSkillButtonClick);
    byId("delete-skill-button").addEventListener("click", handleDeleteSkillButtonClick);
    byId("send-button").addEventListener("click", handleSendButtonClick);
}

document.addEventListener("DOMContentLoaded", initCvDashboard);
