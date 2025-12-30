# ------- BUILD STAGE -------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app/

# Copy only pom.xml first to cache dependencies
COPY pom.xml .

# Copy src folder
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests


# ------- RUNTIME STAGE -------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run applicationdocker logs dgnravepay-backend
                 #
                 #  .   ____          _            __ _ _
                 # /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
                 #( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
                 # \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
                 #  '  |____| .__|_| |_|_| |_\__, | / / / /
                 # =========|_|==============|___/=/_/_/_/
                 #
                 # :: Spring Boot ::                (v3.5.9)
                 #
                 #2025-12-30T17:39:48.999Z  INFO 1 --- [dgnravepay] [           main] b.b.dgnravepay.DgnravepayApplication     : Starting DgnravepayApplication v0.0.1-SNAPSHOT using Java 21.0.9 with PID 1 (/app/app.jar started by root in /app)
                 #2025-12-30T17:39:49.047Z  INFO 1 --- [dgnravepay] [           main] b.b.dgnravepay.DgnravepayApplication     : No active profile set, falling back to 1 default profile: "default"
                 #2025-12-30T17:40:01.416Z  INFO 1 --- [dgnravepay] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode
                 #2025-12-30T17:40:01.441Z  INFO 1 --- [dgnravepay] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
                 #2025-12-30T17:40:05.550Z  INFO 1 --- [dgnravepay] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 4011 ms. Found 5 JPA repository interfaces.
                 #2025-12-30T17:40:05.589Z  INFO 1 --- [dgnravepay] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode
                 #2025-12-30T17:40:05.595Z  INFO 1 --- [dgnravepay] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
                 #2025-12-30T17:40:10.425Z  INFO 1 --- [dgnravepay] [           main] .RepositoryConfigurationExtensionSupport : Spring Data Redis - Could not safely identify store assignment for repository candidate interface business.banking.dgnravepay.auth.repository.DeviceTrustRepository; If you want this repository to be a Redis repository, consider annotating your entities with one of these annotations: org.springframework.data.redis.core.RedisHash (preferred), or consider extending one of the following types with your repository: org.springframework.data.keyvalue.repository.KeyValueRepository
                 #2025-12-30T17:40:10.426Z  INFO 1 --- [dgnravepay] [           main] .RepositoryConfigurationExtensionSupport : Spring Data Redis - Could not safely identify store assignment for repository candidate interface business.banking.dgnravepay.auth.repository.FraudLogRepository; If you want this repository to be a Redis repository, consider annotating your entities with one of these annotations: org.springframework.data.redis.core.RedisHash (preferred), or consider extending one of the following types with your repository: org.springframework.data.keyvalue.repository.KeyValueRepository
                 #2025-12-30T17:40:10.427Z  INFO 1 --- [dgnravepay] [           main] .RepositoryConfigurationExtensionSupport : Spring Data Redis - Could not safely identify store assignment for repository candidate interface business.banking.dgnravepay.auth.repository.OtpRequestRepository; If you want this repository to be a Redis repository, consider annotating your entities with one of these annotations: org.springframework.data.redis.core.RedisHash (preferred), or consider extending one of the following types with your repository: org.springframework.data.keyvalue.repository.KeyValueRepository
                 #2025-12-30T17:40:10.428Z  INFO 1 --- [dgnravepay] [           main] .RepositoryConfigurationExtensionSupport : Spring Data Redis - Could not safely identify store assignment for repository candidate interface business.banking.dgnravepay.auth.repository.RefreshTokenRepository; If you want this repository to be a Redis repository, consider annotating your entities with one of these annotations: org.springframework.data.redis.core.RedisHash (preferred), or consider extending one of the following types with your repository: org.springframework.data.keyvalue.repository.KeyValueRepository
                 #2025-12-30T17:40:10.438Z  INFO 1 --- [dgnravepay] [           main] .RepositoryConfigurationExtensionSupport : Spring Data Redis - Could not safely identify store assignment for repository candidate interface business.banking.dgnravepay.auth.repository.UserRepository; If you want this repository to be a Redis repository, consider annotating your entities with one of these annotations: org.springframework.data.redis.core.RedisHash (preferred), or consider extending one of the following types with your repository: org.springframework.data.keyvalue.repository.KeyValueRepository
                 #2025-12-30T17:40:10.438Z  INFO 1 --- [dgnravepay] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 43 ms. Found 0 Redis repository interfaces.
                 #2025-12-30T17:40:32.500Z  INFO 1 --- [dgnravepay] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
                 #2025-12-30T17:40:32.673Z  INFO 1 --- [dgnravepay] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
                 #2025-12-30T17:40:32.675Z  INFO 1 --- [dgnravepay] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.50]
                 #2025-12-30T17:40:33.973Z  INFO 1 --- [dgnravepay] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
                 #2025-12-30T17:40:34.059Z  INFO 1 --- [dgnravepay] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 44320 ms
                 #2025-12-30T17:40:36.487Z  INFO 1 --- [dgnravepay] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
                 #2025-12-30T17:40:37.285Z  INFO 1 --- [dgnravepay] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.6.39.Final
                 #2025-12-30T17:40:37.738Z  INFO 1 --- [dgnravepay] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
                 #2025-12-30T17:40:45.054Z  INFO 1 --- [dgnravepay] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
                 #2025-12-30T17:40:45.367Z  INFO 1 --- [dgnravepay] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
                 #2025-12-30T17:40:45.545Z  WARN 1 --- [dgnravepay] [           main] org.postgresql.util.PGPropertyUtil       : JDBC URL invalid port number: ${DB_PORT}
                 #2025-12-30T17:40:45.547Z  WARN 1 --- [dgnravepay] [           main] o.h.e.j.e.i.JdbcEnvironmentInitiator     : HHH000342: Could not obtain connection to query metadata
                 #
                 #java.lang.RuntimeException: Driver org.postgresql.Driver claims to not accept jdbcUrl, jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}?sslmode=${DB_SSL}
                 #        at com.zaxxer.hikari.util.DriverDataSource.<init>(DriverDataSource.java:116) ~[HikariCP-6.3.3.jar!/:na]
                 #        at com.zaxxer.hikari.pool.PoolBase.initializeDataSource(PoolBase.java:333) ~[HikariCP-6.3.3.jar!/:na]
                 #        at com.zaxxer.hikari.pool.PoolBase.<init>(PoolBase.java:118) ~[HikariCP-6.3.3.jar!/:na]
                 #        at com.zaxxer.hikari.pool.HikariPool.<init>(HikariPool.java:90) ~[HikariCP-6.3.3.jar!/:na]
                 #        at com.zaxxer.hikari.HikariDataSource.getConnection(HikariDataSource.java:111) ~[HikariCP-6.3.3.jar!/:na]
                 #        at org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl.getConnection(DatasourceConnectionProviderImpl.java:126) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess.obtainConnection(JdbcEnvironmentInitiator.java:485) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcIsolationDelegate.delegateWork(JdbcIsolationDelegate.java:61) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.getJdbcEnvironmentUsingJdbcMetadata(JdbcEnvironmentInitiator.java:334) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:129) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:81) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:130) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:238) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:215) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.boot.model.relational.Database.<init>(Database.java:45) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.getDatabase(InFlightMetadataCollectorImpl.java:226) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.<init>(InFlightMetadataCollectorImpl.java:194) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:171) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1442) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1513) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:66) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:390) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:419) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:400) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:366) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1873) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1822) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:607) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:365) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary(BeanDefinitionValueResolver.java:135) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.resolveConstructorArguments(ConstructorResolver.java:691) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:513) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1375) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1205) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:365) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary(BeanDefinitionValueResolver.java:135) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues(AbstractAutowireCapableBeanFactory.java:1725) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1474) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:606) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1770) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1653) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:913) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:240) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1395) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1232) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1770) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1653) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:913) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:240) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1395) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1232) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:207) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.getOrderedBeansOfType(ServletContextInitializerBeans.java:230) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.addAsRegistrationBean(ServletContextInitializerBeans.java:184) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.addAsRegistrationBean(ServletContextInitializerBeans.java:179) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.addAdaptableBeans(ServletContextInitializerBeans.java:164) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.<init>(ServletContextInitializerBeans.java:96) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.getServletContextInitializerBeans(ServletWebServerApplicationContext.java:271) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.selfInitialize(ServletWebServerApplicationContext.java:245) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatStarter.onStartup(TomcatStarter.java:52) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:4452) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1201) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1191) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at java.base/java.util.concurrent.FutureTask.run(Unknown Source) ~[na:na]
                 #        at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:81) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at java.base/java.util.concurrent.AbstractExecutorService.submit(Unknown Source) ~[na:na]
                 #        at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:747) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.StandardHost.startInternal(StandardHost.java:783) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1201) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1191) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at java.base/java.util.concurrent.FutureTask.run(Unknown Source) ~[na:na]
                 #        at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:81) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at java.base/java.util.concurrent.AbstractExecutorService.submit(Unknown Source) ~[na:na]
                 #        at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:747) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.StandardEngine.startInternal(StandardEngine.java:201) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.StandardService.startInternal(StandardService.java:410) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.StandardServer.startInternal(StandardServer.java:868) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.startup.Tomcat.start(Tomcat.java:436) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatWebServer.initialize(TomcatWebServer.java:128) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatWebServer.<init>(TomcatWebServer.java:107) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.getTomcatWebServer(TomcatServletWebServerFactory.java:517) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.getWebServer(TomcatServletWebServerFactory.java:219) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.createWebServer(ServletWebServerApplicationContext.java:193) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:167) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:621) ~[spring-context-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:752) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.run(SpringApplication.java:318) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1361) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1350) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at business.banking.dgnravepay.DgnravepayApplication.main(DgnravepayApplication.java:10) ~[!/:0.0.1-SNAPSHOT]
                 #        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Unknown Source) ~[na:na]
                 #        at java.base/java.lang.reflect.Method.invoke(Unknown Source) ~[na:na]
                 #        at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:106) ~[app.jar:0.0.1-SNAPSHOT]
                 #        at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:64) ~[app.jar:0.0.1-SNAPSHOT]
                 #        at org.springframework.boot.loader.launch.JarLauncher.main(JarLauncher.java:40) ~[app.jar:0.0.1-SNAPSHOT]
                 #
                 #2025-12-30T17:40:46.442Z  WARN 1 --- [dgnravepay] [           main] org.hibernate.orm.deprecation            : HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
                 #2025-12-30T17:40:46.544Z  INFO 1 --- [dgnravepay] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
                 #        Database JDBC URL [Connecting through datasource 'HikariDataSource (null)']
                 #        Database driver: undefined/unknown
                 #        Database version: 12.0
                 #        Autocommit mode: undefined/unknown
                 #        Isolation level: undefined/unknown
                 #        Minimum pool size: undefined/unknown
                 #        Maximum pool size: undefined/unknown
                 #2025-12-30T17:40:56.391Z  INFO 1 --- [dgnravepay] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
                 #2025-12-30T17:40:56.558Z  INFO 1 --- [dgnravepay] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
                 #2025-12-30T17:40:56.560Z  WARN 1 --- [dgnravepay] [           main] org.postgresql.util.PGPropertyUtil       : JDBC URL invalid port number: ${DB_PORT}
                 #2025-12-30T17:40:56.604Z ERROR 1 --- [dgnravepay] [           main] j.LocalContainerEntityManagerFactoryBean : Failed to initialize JPA EntityManagerFactory: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is java.lang.RuntimeException: Driver org.postgresql.Driver claims to not accept jdbcUrl, jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}?sslmode=${DB_SSL}
                 #2025-12-30T17:40:56.620Z ERROR 1 --- [dgnravepay] [           main] o.s.b.web.embedded.tomcat.TomcatStarter  : Error starting Tomcat context. Exception: org.springframework.beans.factory.UnsatisfiedDependencyException. Message: Error creating bean with name 'jwtAuthenticationFilter' defined in URL [jar:nested:/app/app.jar/!BOOT-INF/classes/!/business/banking/dgnravepay/auth/security/JwtAuthenticationFilter.class]: Unsatisfied dependency expressed through constructor parameter 1: Error creating bean with name 'customUserDetailsService' defined in URL [jar:nested:/app/app.jar/!BOOT-INF/classes/!/business/banking/dgnravepay/auth/service/CustomUserDetailsService.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'userRepository' defined in business.banking.dgnravepay.auth.repository.UserRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Cannot resolve reference to bean 'jpaSharedEM_entityManagerFactory' while setting bean property 'entityManager'
                 #2025-12-30T17:40:56.852Z  INFO 1 --- [dgnravepay] [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
                 #2025-12-30T17:40:56.888Z  WARN 1 --- [dgnravepay] [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.context.ApplicationContextException: Unable to start web server
                 #2025-12-30T17:40:56.982Z  INFO 1 --- [dgnravepay] [           main] .s.b.a.l.ConditionEvaluationReportLogger :
                 #
                 #Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
                 #2025-12-30T17:40:57.216Z ERROR 1 --- [dgnravepay] [           main] o.s.boot.SpringApplication               : Application run failed
                 #
                 #org.springframework.context.ApplicationContextException: Unable to start web server
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:170) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:621) ~[spring-context-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:752) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.run(SpringApplication.java:318) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1361) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1350) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at business.banking.dgnravepay.DgnravepayApplication.main(DgnravepayApplication.java:10) ~[!/:0.0.1-SNAPSHOT]
                 #        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Unknown Source) ~[na:na]
                 #        at java.base/java.lang.reflect.Method.invoke(Unknown Source) ~[na:na]
                 #        at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:106) ~[app.jar:0.0.1-SNAPSHOT]
                 #        at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:64) ~[app.jar:0.0.1-SNAPSHOT]
                 #        at org.springframework.boot.loader.launch.JarLauncher.main(JarLauncher.java:40) ~[app.jar:0.0.1-SNAPSHOT]
                 #Caused by: org.springframework.boot.web.server.WebServerException: Unable to start embedded Tomcat
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatWebServer.initialize(TomcatWebServer.java:147) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatWebServer.<init>(TomcatWebServer.java:107) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.getTomcatWebServer(TomcatServletWebServerFactory.java:517) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.getWebServer(TomcatServletWebServerFactory.java:219) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.createWebServer(ServletWebServerApplicationContext.java:193) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:167) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        ... 13 common frames omitted
                 #Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'jwtAuthenticationFilter' defined in URL [jar:nested:/app/app.jar/!BOOT-INF/classes/!/business/banking/dgnravepay/auth/security/JwtAuthenticationFilter.class]: Unsatisfied dependency expressed through constructor parameter 1: Error creating bean with name 'customUserDetailsService' defined in URL [jar:nested:/app/app.jar/!BOOT-INF/classes/!/business/banking/dgnravepay/auth/service/CustomUserDetailsService.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'userRepository' defined in business.banking.dgnravepay.auth.repository.UserRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Cannot resolve reference to bean 'jpaSharedEM_entityManagerFactory' while setting bean property 'entityManager'
                 #        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:804) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:240) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1395) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1232) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:207) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.getOrderedBeansOfType(ServletContextInitializerBeans.java:230) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.addAsRegistrationBean(ServletContextInitializerBeans.java:184) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.addAsRegistrationBean(ServletContextInitializerBeans.java:179) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.addAdaptableBeans(ServletContextInitializerBeans.java:164) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.ServletContextInitializerBeans.<init>(ServletContextInitializerBeans.java:96) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.getServletContextInitializerBeans(ServletWebServerApplicationContext.java:271) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.selfInitialize(ServletWebServerApplicationContext.java:245) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatStarter.onStartup(TomcatStarter.java:52) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:4452) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1201) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1191) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at java.base/java.util.concurrent.FutureTask.run(Unknown Source) ~[na:na]
                 #        at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:81) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at java.base/java.util.concurrent.AbstractExecutorService.submit(Unknown Source) ~[na:na]
                 #        at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:747) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.StandardHost.startInternal(StandardHost.java:783) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1201) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1191) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at java.base/java.util.concurrent.FutureTask.run(Unknown Source) ~[na:na]
                 #        at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:81) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at java.base/java.util.concurrent.AbstractExecutorService.submit(Unknown Source) ~[na:na]
                 #        at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:747) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.StandardEngine.startInternal(StandardEngine.java:201) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.StandardService.startInternal(StandardService.java:410) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.core.StandardServer.startInternal(StandardServer.java:868) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:164) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.apache.catalina.startup.Tomcat.start(Tomcat.java:436) ~[tomcat-embed-core-10.1.50.jar!/:na]
                 #        at org.springframework.boot.web.embedded.tomcat.TomcatWebServer.initialize(TomcatWebServer.java:128) ~[spring-boot-3.5.9.jar!/:3.5.9]
                 #        ... 18 common frames omitted
                 #Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'customUserDetailsService' defined in URL [jar:nested:/app/app.jar/!BOOT-INF/classes/!/business/banking/dgnravepay/auth/service/CustomUserDetailsService.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'userRepository' defined in business.banking.dgnravepay.auth.repository.UserRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Cannot resolve reference to bean 'jpaSharedEM_entityManagerFactory' while setting bean property 'entityManager'
                 #        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:804) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:240) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1395) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1232) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1770) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1653) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:913) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        ... 59 common frames omitted
                 #Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'userRepository' defined in business.banking.dgnravepay.auth.repository.UserRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Cannot resolve reference to bean 'jpaSharedEM_entityManagerFactory' while setting bean property 'entityManager'
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:377) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary(BeanDefinitionValueResolver.java:135) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues(AbstractAutowireCapableBeanFactory.java:1725) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1474) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:606) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1770) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1653) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:913) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        ... 73 common frames omitted
                 #Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaSharedEM_entityManagerFactory': Cannot resolve reference to bean 'entityManagerFactory' while setting constructor argument
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:377) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary(BeanDefinitionValueResolver.java:135) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.resolveConstructorArguments(ConstructorResolver.java:691) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:513) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1375) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1205) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:365) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        ... 87 common frames omitted
                 #Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is java.lang.RuntimeException: Driver org.postgresql.Driver claims to not accept jdbcUrl, jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}?sslmode=${DB_SSL}
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1826) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:607) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:365) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        ... 99 common frames omitted
                 #Caused by: jakarta.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is java.lang.RuntimeException: Driver org.postgresql.Driver claims to not accept jdbcUrl, jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}?sslmode=${DB_SSL}
                 #        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:431) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:400) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:366) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1873) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1822) ~[spring-beans-6.2.15.jar!/:6.2.15]
                 #        ... 106 common frames omitted
                 #Caused by: java.lang.RuntimeException: Driver org.postgresql.Driver claims to not accept jdbcUrl, jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}?sslmode=${DB_SSL}
                 #        at com.zaxxer.hikari.util.DriverDataSource.<init>(DriverDataSource.java:116) ~[HikariCP-6.3.3.jar!/:na]
                 #        at com.zaxxer.hikari.pool.PoolBase.initializeDataSource(PoolBase.java:333) ~[HikariCP-6.3.3.jar!/:na]
                 #        at com.zaxxer.hikari.pool.PoolBase.<init>(PoolBase.java:118) ~[HikariCP-6.3.3.jar!/:na]
                 #        at com.zaxxer.hikari.pool.HikariPool.<init>(HikariPool.java:90) ~[HikariCP-6.3.3.jar!/:na]
                 #        at com.zaxxer.hikari.HikariDataSource.getConnection(HikariDataSource.java:111) ~[HikariCP-6.3.3.jar!/:na]
                 #        at org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl.getConnection(DatasourceConnectionProviderImpl.java:126) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess.obtainConnection(JdbcEnvironmentInitiator.java:485) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl.getIsolatedConnection(DdlTransactionIsolatorNonJtaImpl.java:46) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl.getIsolatedConnection(DdlTransactionIsolatorNonJtaImpl.java:39) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.internal.exec.ImprovedExtractionContextImpl.getJdbcConnection(ImprovedExtractionContextImpl.java:63) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.extract.spi.ExtractionContext.getQueryResults(ExtractionContext.java:43) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorLegacyImpl.extractMetadata(SequenceInformationExtractorLegacyImpl.java:39) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.extract.internal.DatabaseInformationImpl.initializeSequences(DatabaseInformationImpl.java:66) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.extract.internal.DatabaseInformationImpl.<init>(DatabaseInformationImpl.java:60) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.internal.Helper.buildDatabaseInformation(Helper.java:185) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.doMigration(AbstractSchemaMigrator.java:93) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.performDatabaseAction(SchemaManagementToolCoordinator.java:280) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.lambda$process$5(SchemaManagementToolCoordinator.java:144) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at java.base/java.util.HashMap.forEach(Unknown Source) ~[na:na]
                 #        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.process(SchemaManagementToolCoordinator.java:141) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.boot.internal.SessionFactoryObserverForSchemaExport.sessionFactoryCreated(SessionFactoryObserverForSchemaExport.java:37) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.internal.SessionFactoryObserverChain.sessionFactoryCreated(SessionFactoryObserverChain.java:35) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:324) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:463) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1517) ~[hibernate-core-6.6.39.Final.jar!/:6.6.39.Final]
                 #        at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:66) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:390) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:419) ~[spring-orm-6.2.15.jar!/:6.2.15]
                 #        ... 110 common frames omitted
                 #
                 #PS C:\Users\HP\source\dgnravepay\dgnravepay>
ENTRYPOINT ["java","-jar","app.jar"]
