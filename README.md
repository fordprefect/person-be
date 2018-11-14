## Person Back-end API

### Build and deploy (listens on port 8190)
`mvn clean spring-boot:run`

### Using Postman within Google Chrome or your favorite web testing 
### tool (e.g. curl), invoke the GET HTTP method on the following URIs:

### Person by ID

URI: `/people/1`

Expected: 
`{"id":1,"firstName":"Rai","lastName":"Singh","email":"rsingh@edmunds.com"}`

### Person by email

URI: `/people/email/cnN3YW5zb25AZWRtdW5kcy5jb20=`

Expected: 
`{"id":3,"firstName":"Ron","lastName":"Swanson","email":"rswanson@edmunds.com"}`
