db = db.getSiblingDB("shelters");

db.createUser({
    user: "dar",
    pwd: "dar",
    roles: [{ role: "readWrite", db: "shelters" }]
});