# News Mood Analyzer

News Mood Analyzer is an application that analyzes the emotional content of news articles or posts using OpenAI's ChatGPT language model. It uses natural language processing techniques to determine the presence and intensity of various emotions in the text.

## Features

- Analyzes the emotional content of news articles or posts
- Provides evaluations for emotions such as joy, sadness, fear, anger, surprise, and more
- Stores evaluations in a database for further analysis or visualization

## Technologies Used

- Java
- Spring Framework
- OpenAI GPT-3.5 Turbo
- MySQL (or any other supported database)

## Getting Started

These instructions will guide you on how to set up and run the News Mood Analyzer application on your local machine for development and testing purposes.

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven (for dependency management)
- MySQL or any other supported database

### Installation

1. Clone the repository from GitHub:

```shell
git clone https://github.com/dsawka/news-mood-analyzer.git
```

2. Navigate to the project directory:

```shell'
cd news-mood-analyzer
```

3. Build the project using Maven:

```shell
mvn clean install
```

### Configuration

Open the application.properties file located in src/main/resources.

Configure the database connection properties such as URL, username, and password:

'''properties
spring.datasource.url=jdbc:mysql://localhost:3306/news_mood_analyzer?useSSL=false&serverTimezone=UTC
spring.datasource.username=your-username
spring.datasource.password=your-password
'''
Provide your OpenAI API key by setting the OPENAI_API_KEY environment variable or updating the OpenAiApiKey.API_KEY constant in the ChatGPTHelper class.

### Usage
Run the application:

```shell
java -jar target/news-mood-analyzer.jar
```
