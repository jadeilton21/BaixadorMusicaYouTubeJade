```mermaid
graph TD
    %% Entradas do usuário
    A[Usuário] --> B[Campo de Texto - YouTube URL]
    A --> C[Campo de Texto - Filme URL - mp4]

    %% Botões
    B --> D[Botão "Baixar Áudio"]
    C --> E[Botão "Baixar Filme"]

    %% Fluxo de Download do YouTube
    D --> F[Função baixarAudioYouTube()]
    F --> G[Valida URL do YouTube]
    G -->|Vazia| H1[Exibe erro: "Informe a URL do YouTube!"]
    G -->|Válida| H2[Cria pasta MusicasYouTube na Área de Trabalho]
    H2 --> H3[Executa yt-dlp com argumentos]
    H3 --> H4[Exibe sucesso ou erro]

    %% Fluxo de Download de Filmes
    E --> I[Função baixarFilmeDireto()]
    I --> J[Valida URL .mp4]
    J -->|Vazia| K1[Exibe erro: "Informe a URL do arquivo .mp4!"]
    J -->|Válida| K2[Cria pasta FilmesJade na Área de Trabalho]
    K2 --> K3[Abre InputStream da URL]
    K3 --> K4[Salva arquivo como filme_TIMESTAMP.mp4]
    K4 --> K5[Exibe sucesso ou erro]

    %% Agrupamento
    subgraph Interface Gráfica (Swing)
        B
        C
        D
        E
    end

    subgraph Lógica de Negócio
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

