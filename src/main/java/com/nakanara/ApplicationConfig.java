package com.nakanara;

import com.nakanara.core.component.NormalPasswordEncoder;
import com.nakanara.core.config.LoginFailureHandler;
import com.nakanara.core.config.LoginSuccessHandler;
import com.nakanara.core.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableJpaAuditing
@EnableWebSecurity
@SpringBootApplication
@Slf4j
@ComponentScan(value = "com.nakanara.*.*")
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    private static final int TX_METHOD_TIMEOUT = 3;

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.nakanara.*.service..*.*(..))";


    private DataSourceTransactionManager transactionManager;

    private MemberService memberService;

    private PasswordEncoder passwordEncoder;

    private LoginSuccessHandler loginSuccessHandler;

    private LoginFailureHandler loginFailureHandler;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    @Autowired
    public void setLoginSuccessHandler(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void setLoginFailureHandler(LoginFailureHandler loginFailureHandler) {
        this.loginFailureHandler = loginFailureHandler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // https://bamdule.tistory.com/53
//        https://kitty-geno.tistory.com/131
        http
                .authorizeRequests()
                .antMatchers("/", "/signup").permitAll()
                .anyRequest().authenticated()
                ;

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder);

    }

//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

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
