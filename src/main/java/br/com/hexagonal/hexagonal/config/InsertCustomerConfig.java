package br.com.hexagonal.hexagonal.config;

import br.com.hexagonal.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import br.com.hexagonal.hexagonal.adapters.out.InsertCustomerAdapter;
import br.com.hexagonal.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            InsertCustomerAdapter insertCustomerAdapter
    ) {
        return new InsertCustomerUseCase(findAddressByZipCodeAdapter, insertCustomerAdapter);
    }

}
