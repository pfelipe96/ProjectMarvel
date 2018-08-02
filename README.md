Marvel APP

Projeto para teste de conhecimento.

## App construido com Dagger2, Retrofit, RxJava/Kotlin, Gson e Arquitetura MVP.

O principal objetivo da aplicação listar os personagens da marvel e pode vê a mais sobre o mesmo.

## Como o projeto está estruturado:

- AppWeather
   - api (Retrofit e RxJava/Kotlin) - Retrofit responsável por manipular api e fazer comunição da mesma e RxJava/Kotlin responsável por fazer o gerenciamento do returno da api
      - MarveAPi
      
   - Adapter
     - adaptercomics
        - AdapterImageComics
        - ImageComicVH
        
     - adapterpersonage
        - AdapterPersonageMarvel
        - PersonageMarvelVH
      
   - data (Gson) - Classes da dados já inicializadas com Gson(Serialize), facilitando a manipulação do restful.
      - comics
        - ComicsDataDetail
        - ComicsListData
        - ResultsListComicsData
        
      - marvel personagens
        - ComicsData
        - PersonageData
        - PersonagesData
        - ResultsListData
        - ThumbnailData
     
   - di (Dagger)
      - component
        - ApplicationComponent - reponsável de fornece as instâncias dos module para classes injetadas
        
      - module
        - ApiModule - Criar uma instância static do Retrofit e RxJava
        - ApplicationModule - Criar instâncias da Application e Gson
        
   - mvp
      - model - package responsável por puxar as informações da api ou persistência
        - MainModel
        - MainModelInterface
        
      - presenter - package responsável por fazer o two-way entre model e view
        - MainPresenter 
        - MainPresenterInterface
   
      - ui - package responsável de configurar os dados vindo do presenter para views
        - MainActivity
        - MainActivityInterface
        - DetailPersonage
      
   - MainApplication - Inicialização do Dagger
   - Constants - Guarda as chaves de requisição da api marvel
   
   **OBS: No skip da Marvel, aparentemente está meio bugado, portando eu coloquei o skip para 20 em 20, para não aparecer personagens repetidos.
