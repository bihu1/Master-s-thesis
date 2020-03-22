db.auth('admin-user', 'admin-password');

db = db.getSiblingDB('functional8m');

db.createUser({
    user: 'test-user',
    pwd: 'test-password',
    roles: [
        {
            role: 'root',
            db: 'admin',
        },
    ],
});
db.functional8m.insert({ name: 'test' })


db = db.getSiblingDB('reactive8mr');

db.createUser({
    user: 'test-user2',
    pwd: 'test-password',
    roles: [
        {
            role: 'root',
            db: 'admin',
        },
    ],
});

db.reactive8mr.insert({ name: 'test' })