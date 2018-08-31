Locus assignment

Roles based authentication and authorization

Assumption 
 -- there is a superadmin who has every access read,write,delete on each resource instance
 -- he can grant permisison to any user
 -- already exists in db

Have used controllers advice mechanism where exception of controller can be handled at a central location and apt response can be send
 
Entites used :
 -- User (username, password, isSuperadmin)
 -- Resource( name eg post,articles)
 -- Resource instance ( instance of resource , resource_id(fk), description)
 -- Permission(userid,resourceid,access)(userid,resourceid are unique ie unique constraints on them)
 -- Access( enum (read,write,delete)
 
Controllers
 -- /user/signin any user can register into system providing username and password
 -- access/{resource_id}?permission={read,write,delete}&userId={userid}
    ( only superadmin can grant access on each resource is isuperadmin=1,
      resource_id : id of resource which needs authorization
      userid: id of user that needs access
      every user can have read or write or delete permission on a resource)
      
 -- resources/{resource_id}/instance/{resource_instance_id} get or read a resource
    (this endpoint needs authentication, username and password, based on that we get user who is accessing the resource, figure out if he has relevant 
      permisisons if yes success else unauthorized)
      
 -- resources/{resource_id}/instance/ post or write a resource 
    (this endpoint needs authentication, username and password, based on that we get user who is accessing the resource, figure out if he has relevant 
      permisisons if yes success else unauthorized)
 
 -- resources/{resource_id}/instance/{resource_id} delete  resource 
    (this endpoint needs authentication, username and password, based on that we get user who is accessing the resource, figure out if he has relevant 
      permisisons if yes success else unauthorized)    
      
 
 Further improvements
 -- project has very basic setup
 -- authenication can be done through JWT tokens 
 -- services only need to contain business logic, could have implemented controller impl from where I could have return the response 
     instead from service
  --    logging can be done
  -- swagger specs of api endpoints 