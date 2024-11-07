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
    jwt.secret=your_secret_key
    jwt.access.token.expiry=600000    # 10 minutes
    jwt.refresh.token.expiry=2592000000 # 30 days
 
</details>
