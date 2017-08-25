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
     "_id" : "Training",
    "_class": "com.anthem.oss.nimbus.core.entity.client.ClientEntity",
    "name" : "Training",
    "fedTaxID": "1234567890"
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