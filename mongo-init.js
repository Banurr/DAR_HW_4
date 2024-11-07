db = db.getSiblingDB("shelter");

db.createUser({
    user: "dar",
    pwd: "dar",
    roles: [{ role: "readWrite", db: "shelters" }]
});