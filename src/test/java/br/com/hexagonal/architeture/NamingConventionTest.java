package br.com.hexagonal.architeture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.com.hexagonal")
public class NamingConventionTest {

    //teste de verificacao se as classes estao no pacote correto
    //Para criar um teste de falha, digite o nome Consumer em um pacote fora do resideInAPackage
    @ArchTest
    public static final ArchRule consumer_reside_only_consumer_package = classes()
            .that()
            .haveNameMatching(".*Consumer") //sufixo (final da palavra, no caso todos que tem final Consumer)
            .should()
            .resideInAPackage("..adapters.in.consumer")
            .as("Classe Consumer encontrada fora do pacote definido (adapters.in.consumer)"); //descricao do erro

    //Apontando que a classe com esse nome final "Mapper" reside em varios pacotes
    @ArchTest
    public static final ArchRule mapper_reside_only_mapper_package = classes()
            .that()
            .haveNameMatching(".*Mapper")
            .should()
            .resideInAnyPackage("..adapters.in.consumer.mapper",
                    "..adapters.in.controller.mapper",
                    "..adapters.out.client.mapper",
                    "..adapters.out.repository.mapper")
            .as("Classe Mapper esta fora dos pacotes definidos no metodo AnyPackage !");

    //Teste para verificar se dentro de um pacote (nesse caso o consumer), possui alguma classe sem o final "Consumer"
    //ou seja, se criar uma classe dentro do pacote sem ter escrito o final Consumer , ele estoura erro
    //para outros nomes "ConsumerImpl" por exemplo, podemos utilizar o metodo orShould
    @ArchTest
    public static final ArchRule should_be_suffixed_consumer = classes()
            .that()
            .resideInAnyPackage("..consumer")
            .should()
            .haveSimpleNameEndingWith("Consumer");

}
