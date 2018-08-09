show dbs
use training
db.dropDatabase()

db.staticCodeValue.insert([
{ 
    "_class": "com.anthem.oss.nimbus.core.entity.StaticCodeValue",  
    "paramCode": "/orgType",  
    "paramValues": [ { 
        "code": "ORG", 
        "label": 
        "Organization", 
        "isActive": true 
    },  
    { "code": "CLIENT", 
    "label": "Client", 
    "isActive": true 
    }] 
 }
 ]);

db.cliententity.insert([
 {
    "_class": "com.anthem.oss.nimbus.core.entity.client.ClientEntity",
    "name" : "Training",
    "fedTaxID": "1234567890"
 },
 {
	    "_id" : NumberLong("1"),
	    "class" : "com.antheminc.oss.nimbus.entity.client.Client",
	    "code" : "petsmart",
	    "name" : "PetSmart",
	    "type" : "CLIENT",
	    "nestedEntity" : [ 
	        {
	            "_id" : NumberLong("2"),
	            "code" : "petshop",
	            "type" : "ORG"
	        }, 
	        {
	            "_id" : NumberLong("3"),
	            "code" : "petcare",
	            "type" : "ORG"
	        }
	    ]
	},

	/* 2 */
	{
	    "_id" : NumberLong("2"),
	    "class" : "com.antheminc.oss.nimbus.entity.client.ClientEntity",
	    "code" : "petshop",
	    "name" : "Pet Shop",
	    "type" : "ORG",
	    "parentEntity" : NumberLong("1"),
	    "nestedEntity" : [ 
	        {
	            "_id" : NumberLong("5"),
	            "code" : "petzoo",
	            "type" : "APP"
	        }, 
	        {
	            "_id" : NumberLong("6"),
	            "code" : "littlepugs",
	            "type" : "APP"
	        }
	    ]
	},

	/* 3 */
	{
	    "_id" : NumberLong("3"),
	    "class" : "com.antheminc.oss.nimbus.entity.client.ClientEntity",
	    "code" : "petcare",
	    "name" : "Pet Care",
	    "type" : "ORG",
	    "parentEntity" : NumberLong("1"),
	    "nestedEntity" : [ 
	        {
	            "_id" : NumberLong("4"),
	            "code" : "petmd",
	            "type" : "APP"
	        }
	    ]
	},

	/* 4 */
	{
	    "_id" : NumberLong("4"),
	    "class" : "com.antheminc.oss.nimbus.entity.client.ClientEntity",
	    "code" : "petmd",
	    "name" : "Pet MD",
	    "description" : "Pet Medical Care",
	    "type" : "APP",
	    "parentEntity" : NumberLong("3")
	},

	/* 5 */
	{
	    "_id" : NumberLong("5"),
	    "class" : "com.antheminc.oss.nimbus.entity.client.ClientEntity",
	    "code" : "petzoo",
	    "name" : "Pet Zoo",
	    "description" : "Pet Zoo",
	    "type" : "APP",
	    "parentEntity" : NumberLong("2")
	},

	/* 6 */
	{
	    "_id" : NumberLong("6"),
	    "class" : "com.antheminc.oss.nimbus.entity.client.ClientEntity",
	    "code" : "littlepugs",
	    "name" : "Little Pugs",
	    "type" : "APP",
	    "parentEntity" : NumberLong("2")
	}
 ]);

db.clientuser.insert([
{
	    "_class" : "com.anthem.oss.nimbus.core.entity.client.user.ClientUser",
	    "displayName" : "training",
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
	    "displayName" : "training",
	    "loginId" : "helloworld",
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
	    "_id" : NumberLong(101),
	    "_class" : "com.anthem.oss.nimbus.core.entity.client.user.ClientUser",
	    "displayName" : "Alabaster Snowball",
	    "loginId" : "asnowball",
	    "location" : "EST",
	    "client" : [ 
	        {
	            "code" : "petsmart",
	            "name" : "PetSmart",
	            "type" : "CLIENT",
	            "nestedEntity" : [ 
	                {
	                    "code" : "petshop",
	                    "name" : "Pet Shop",
	                    "type" : "ORG",
	                    "parentEntity" : NumberLong("1"),
	                    "nestedEntity" : [ 
	                        {
	                            "code" : "petzoo",
	                            "name" : "Pet Zoo",
	                            "type" : "APP",
	                            "parentEntity" : NumberLong("2")
	                        }, 
	                        {
	                            "code" : "littlepugs",
	                            "name" : "Little Pugs",
	                            "type" : "APP",
	                            "parentEntity" : NumberLong("2")
	                        }
	                    ]
	                }, 
	                {
	                    "code" : "petcare",
	                    "name" : "Pet Care",
	                    "type" : "ORG",
	                    "parentEntity" : NumberLong("1"),
	                    "nestedEntity" : [ 
	                        {
	                            "code" : "petmd",
	                            "name" : "Pet MD",
	                            "type" : "APP",
	                            "parentEntity" : NumberLong("4")
	                        }
	                    ]
	                }
	            ]
	        }
	    ],
	    "name" : {
	        "firstName" : "Alabaster",
	        "lastName" : "Snowball"
	    }
	}   
 ]);

db.sequence.insert([
{
	"_class" : "com.anthem.nimbus.platform.spec.model.DBSequence",
	"_id" : "global",
	"seq" : NumberLong(0)
}
]);

db.staticCodeValue.insert([{ "_class": "com.anthem.oss.nimbus.core.entity.StaticCodeValue", 
    "paramCode": "/vetSpecialty", 
    "paramValues": [
        { "code": "AnimalBehavior ", "label": "Animal Behavior", "isActive": true }, 
        { "code": "Birds", "label": "Birds", "isActive": true },
        { "code": "Canine", "label": "Canine", "isActive": true }, 
        { "code": "Dentistry", "label": "Dentistry", "isActive": true },
        { "code": "Feline", "label": "Feline", "isActive": true }, 
        { "code": "Pathology", "label": "Pathology", "isActive": true }
    ] 
}]);

db.staticCodeValue.insert([{ "_class": "com.anthem.oss.nimbus.core.entity.StaticCodeValue", 
    "paramCode": "/country", 
    "paramValues": [
        { "code": "selectOne", "label": "Select One", "isActive": true }, 
        { "code": "usa", "label": "USA", "isActive": true }
    ] 
}]);

db.staticCodeValue.insert([{ "_class": "com.anthem.oss.nimbus.core.entity.StaticCodeValue", 
    "paramCode": "/addressType", 
    "paramValues": [
        { "code": "BILLING", "label": "BILLING", "isActive": true }, 
        { "code": "MAILING", "label": "MAILING", "isActive": true }
    ] 
}]);

show collections