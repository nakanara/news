package com.nakanara.news;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@EnableJpaAuditing
@SpringBootApplication
@Slf4j
public class NewsApplication {

    private static final int TX_METHOD_TIMEOUT = 3;

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.nakanara.service..*.*(..))";


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
        SpringApplication.run(NewsApplication.class, args);
    }


}
