import {Router} from 'express';


const router = Router();


//our in memory data for users
const users = [
    {id: 1, name: "sarah"},
    {id: 2, name: "arwa"},
    {id: 3, name: "bashar"},
];


//send get request to /users to get the list of users 
//and the 200 is the status code for a successful request
router.get("/users", (req, res) => { 
    res.status(200).json(users);
});


router.get("/users/:id", (req, res) => {
    const id = Number(req.params.id);
    const user = users.find((user) => user.id === id);
    if (!user) {
        return res.status(404).json({message: "User not found"});
    }
    res.status(200).json(user);
});

router.post("/users", (req, res) => {
    const {name} = req.body;
    if (!name) {
        return res.status(400).json({message: "Name is required"});
    }
    const newUser = {id: users.length + 1, name};
    users.push(newUser);
    res.status(201).json(newUser);
}); 

export default router;

