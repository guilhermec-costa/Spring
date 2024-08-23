package com.me.SpringApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// marca essa classe como container de bean
// beans, por padrão, tem escopo singleton
// ou seja, é uma única instância em memória compartilhada durante toda a execução da aplicação
@Configuration
public class DbConfig {
    
    // cria um bean. 
    // muito útil para criar instâncias de classes externas
    // de forma que o Spring consiga lidar com a injeção de dependência delas
    // dessa forma, a instância que esse bean retorna fica disponível em todo o contexto da aplicação

    // @Bean sdkAWS sdkAWS()
    // {
    //     return new sdkAWS();
    // }
}
