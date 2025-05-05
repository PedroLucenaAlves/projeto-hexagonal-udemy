package br.com.hexagonal.hexagonal.config;

import br.com.hexagonal.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import br.com.hexagonal.hexagonal.adapters.out.UpdateCustomerAdapter;
import br.com.hexagonal.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import br.com.hexagonal.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerByIdConfig {

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            UpdateCustomerAdapter updateCustomerAdapter
    ) {
        return new UpdateCustomerUseCase(findCustomerByIdUseCase, findAddressByZipCodeAdapter, updateCustomerAdapter);
    }

}
