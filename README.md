# Futurama - MVVM

## Sobre o projeto

Aplicativo Android que exibe uma lista com os personagens da sitcom animada **Futurama**, consumindo dados de uma API REST. O projeto segue a arquitetura **MVVM** com injeção de dependências e UI declarativa.

### Layout mobile

<img src="https://github.com/Brunoandroid/Imagens/blob/main/futurama_all.gif" width="250" height="500"/>

## Funcionalidades

- Tela inicial com logo e preview de imagem em fullscreen
- Listagem de personagens com imagem, nome, status, espécie e gênero
- Tela de detalhes do personagem
- Suporte a tema claro/escuro com toggle na toolbar
- Navegação entre telas com Navigation Compose

## Arquitetura

O projeto utiliza **MVVM (Model-View-ViewModel)** com separação clara de responsabilidades:

```
app/
├── components/         # Componentes reutilizáveis (AppBar, Text, Image, Loading)
├── data/
│   ├── model/          # Data classes (CharacterItem, CharacterResponse)
│   └── repository/     # Repository para acesso a API
├── di/                 # Módulos de injeção de dependência (Hilt)
├── navigation/         # NavGraph e definição de rotas
├── screens/
│   ├── home/           # Tela inicial (splash com logo)
│   ├── initial/        # Lista de personagens
│   └── detail/         # Detalhes do personagem
└── ui/theme/           # Cores, dimensões, tipografia e tema
```

## Tecnologias

| Categoria | Tecnologia |
|-----------|------------|
| Linguagem | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Navegação | Navigation Compose |
| Arquitetura | MVVM |
| DI | Dagger Hilt |
| HTTP | Retrofit + Gson |
| Imagens | Coil |
| Async | Coroutines |
| Estado | LiveData + StateFlow |

## Requisitos

| Requisito | Versão |
|-----------|--------|
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |
| Compile SDK | 34 |
| Java | 1.8 |

## API

O app consome dados da [Futurama API](https://futuramaapi.com/api/).

## Autor

Bruno Freitas Araujo
