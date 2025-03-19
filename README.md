# FunnelSensAI

<details>
  <summary>Local Application Properties Config File</summary>

    # MySQL Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/funnel_sensai
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

    # JWT Configuration
    jwt.secret=7c22b17c7217c20121bba9ea44c4b475337b33544251960215f1009c4b480768f44992682a051cadab310a1bbf69a57be5df2908bad70cf9dbe222cb72e7becc
    jwt.access.token.expiry=600    
    jwt.refresh.token.expiry=2592000
    goHighLevel.mockToken=Bearer 123
    goHighLevel.clientId=67406522d42dfd22d8f95a89-m7dfgg2d
    goHighLevel.clientSecret=be91a4a2-02de-4575-9995-bbf85734d16c
    goHighLevel.redirectUri=http://localhost:8080/auth/callback
 
</details>
