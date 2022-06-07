package com.nakanara;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@EnableJpaAuditing
@SpringBootApplication
@Slf4j
@ComponentScan(value = "com.nakanara.*.*")
public class ApplicationConfig {

    private static final int TX_METHOD_TIMEOUT = 3;

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.nakanara.*.service..*.*(..))";


    private DataSourceTransactionManager transactionManager;
//
//    @Autowired
//    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//    }
//
//    @Bean
//    public TransactionInterceptor txAdvice() {
//
//        TransactionInterceptor txAdvice = new TransactionInterceptor();
//        Properties txAttributes = new Properties();
//
//        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
//
//        rollbackRules.add(new RollbackRuleAttribute(Exception.class));
//
//        /** If need to add additionall exceptio, add here **/
//        DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
//        readOnlyAttribute.setReadOnly(true);
//
//        readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);
//
//
//        RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
//        writeAttribute.setTimeout(TX_METHOD_TIMEOUT);
//
//        String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
//        String writeTransactionAttributesDefinition = writeAttribute.toString();
//
//        log.info("Read Only Attributes :: {}", readOnlyTransactionAttributesDefinition);
//        log.info("Write Attributes :: {}", writeTransactionAttributesDefinition);
//
//
//        // read-only
//        txAttributes.setProperty("retrieve*", readOnlyTransactionAttributesDefinition);
//        txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
//        txAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
//        txAttributes.setProperty("list*", readOnlyTransactionAttributesDefinition);
//        txAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
//        txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
//        txAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);
//
//        // write rollback-rule
//        txAttributes.setProperty("*", writeTransactionAttributesDefinition);
//
//        txAdvice.setTransactionAttributes(txAttributes);
//
//        txAdvice.setTransactionManager(transactionManager);
//
//        return txAdvice;
//    }
//
//    @Bean
//    public Advisor txAdviceAdvisor() {
//
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//
//        return new DefaultPointcutAdvisor(pointcut, txAdvice());
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }


}
