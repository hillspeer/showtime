 build an online movie ticket booking platform that caters to both B2B (theatre partners) 
and B2C (end customers) clients. 


Main language -Java with spring boot, JPA, spring security 
• Frameworks- spring boot, jpa
• Database - postgres
• Integration technologies- restful interface, any message broker and SFTP for ingesting shows into the theatre screens
• Cloud technologies- AWS for notification (SNS for notification, opensearch for logging), prefer EKS for containerized deployment
• Preferred editor to build - intelliJ with gradle wrapper for buuilding


Following funtional feature implemented:

  Tickets booked for the afternoon show get a 20% discount 
  Theatres can create, update, and delete shows for the day. 


database, transactions, and data modelling.
  The main database is showtime with schemas for domain theatre_partner and cutomer pertaining to main domain models.
  Uses optimistic locking to ensure the transactions are not locked during current access to seat availability and booking
  the Aurora Postgres instance with regional read replicas in multiple regions (mumbai and hydrabad) with one region hosting master for write transactions

hosting solution and sizing (Cloud / Hybrid/ Multi cloud)- AWS aurora 

API can be found after the servie is up with:
http://localhost:8081/swagger-ui/index.html#/


Discuss your technology choices and decisions through key drivers 
 
Discuss enterprise systems that you may need to manage specific areas - 
  User management needing Auth provider like OKTA/Ping,
  Data management with CMS
  Payment processing 
  Reporting and analytics with AWS Athena or BI tool

release management across cities, languages: 
  
  the shows are listed location based after user logs in
  release per region done using helm chart

monitoring solution - Prometheus with grafana

High-level project plan and estimates breakup - will be discussed based on sizing of deployment 

10 mn transactions 
