```mermaid
graph TD
    %% Entradas do usuário
    A[Usuário] --> B[Campo de Texto - YouTube URL]
    A --> C[Campo de Texto - Filme URL - mp4]

    %% Botões
    B --> D[Botao Baixar Audio]
    C --> E[Botao Baixar Filme]

    %% Fluxo de Download do YouTube
    D --> F[Funcao baixarAudioYouTube()]
    F --> G[Valida URL do YouTube]
    G -->|Vazia| H1[Exibe erro: Informe a URL do YouTube]
    G -->|Valida| H2[Cria pasta MusicasYouTube na Area de Trabalho]
    H2 --> H3[Executa yt-dlp com argumentos]
    H3 --> H4[Exibe sucesso ou erro]

    %% Fluxo de Download de Filmes
    E --> I[Funcao baixarFilmeDireto()]
    I --> J[Valida URL mp4]
    J -->|Vazia| K1[Exibe erro: Informe a URL do arquivo mp4]
    J -->|Valida| K2[Cria pasta FilmesJade na Area de Trabalho]
    K2 --> K3[Abre InputStream da URL]
    K3 --> K4[Salva arquivo como filme_TIMESTAMP.mp4]
    K4 --> K5[Exibe sucesso ou erro]

    %% Agrupamento
    subgraph Interface Grafica (Swing)
        B
        C
        D
        E
    end

    subgraph Logica de Negocio
        F
        G
        H1
        H2
        H3
        H4
        I
        J
        K1
        K2
        K3
        K4
        K5
    end
```
