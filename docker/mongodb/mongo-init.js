db.createUser(
    {
        user: "shortener_rw",
        pwd: "7QZjSNbg0cor",
        roles: [
            {
                role: "readWrite",
                db: "dbShortener"
            }
        ]
    }
);