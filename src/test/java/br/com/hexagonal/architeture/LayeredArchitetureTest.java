package br.com.hexagonal.architeture;


import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "br.com.hexagonal") //pacotes que iremos analisar
public class LayeredArchitetureTest {

    //teste de restricao a acessos das camadas
    @ArchTest
    public static final ArchRule layered_architeture_test = layeredArchitecture()
            .consideringAllDependencies()
            //cada camada aqui e nomeada e definada por quais outras ela pode ser acessada baseada na arquitetura do projeto
            //se eu definir uma camada que nao pode ter acesso, o teste da erro
            .layer("AdaptersIn").definedBy("..adapters.in..") //nomeando uma camada
            .layer("AdaptersOut").definedBy("..adapters.out..") //nomeando uma camada
            .layer("UseCase").definedBy("..application.core.usecase..") //nomeando uma camada
            .layer("PortsIn").definedBy("..application.ports.in..") //nomeando uma camada
            .layer("PortsOut").definedBy("..application.ports.out..") //nomeando uma camada
            .layer("Config").definedBy("..config..") //nomeando uma camada
            //aqui eu restrinjo quais camadas que podem acessar outras (no caso Config apenas), se por alguma diferente o teste falha
            .whereLayer("AdaptersIn").mayOnlyBeAccessedByLayers("Config")//restrigindo acesso apenas a camada de Config
            .whereLayer("AdaptersOut").mayOnlyBeAccessedByLayers("Config")//restrigindo acesso
            .whereLayer("UseCase").mayOnlyBeAccessedByLayers("Config")//restrigindo acesso
            .whereLayer("PortsIn").mayOnlyBeAccessedByLayers("UseCase", "AdaptersIn")//restrigindo acesso
            .whereLayer("PortsOut").mayOnlyBeAccessedByLayers("UseCase", "AdaptersOut")//restrigindo acesso
            .whereLayer("Config").mayNotBeAccessedByAnyLayer(); //No caso da classe Config, usamos o nao pode ser acessado por nenhuma camada

}
