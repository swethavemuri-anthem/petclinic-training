show dbs

use test

db.dropDatabase()

show dbs

use test

db.cliententity.insert([
{
     "_id" : "Training",
    "_class": "com.anthem.oss.nimbus.core.entity.client.ClientEntity",
    "name" : "Training",
    "fedTaxID": "1234567890"
 }
 ]);

db.clientuser.insert([
{
    "_class" : "com.anthem.oss.nimbus.core.entity.client.user.ClientUser",
    "displayName" : "test3",
    "loginId" : "training",
    "client" : {
        "code" : "Training",
        "name" : "Training"
    },
    "name" : {
        "firstName" : "Nimbus",
        "lastName" : "Training"
    },
    "roleName" : "Nimbus Trainee"
},
{
    "_class" : "com.anthem.oss.nimbus.core.entity.client.user.ClientUser",
    "displayName" : "test3",
    "loginId" : "admin",
    "client" : {
        "code" : "Anthem",
        "name" : "Anthem"
    },
    "name" : {
        "firstName" : "TrainingAdmin",
        "lastName" : "TrainingAdmin"
    },
    "roleName" : "Training Admin"
},
{
    "_class" : "com.anthem.oss.nimbus.core.entity.client.user.ClientUser",
    "displayName" : "test3",
    "loginId" : "supervisor",
    "client" : {
        "code" : "Anthem",
        "name" : "Anthem"
    },
    "name" : {
        "firstName" : "Jane",
        "lastName" : "Johnson"
    },
    "roleName" : "Case Manager Supervisor"
},
{
    "_class" : "com.anthem.oss.nimbus.core.entity.client.user.ClientUser",
    "displayName" : "test3",
    "loginId" : "member",
    "client" : {
        "code" : "Anthem",
        "name" : "Anthem"
    },
    "name" : {
        "firstName" : "Bill",
        "lastName" : "Batten"
    },
    "roleName" : "Member"
}
]);