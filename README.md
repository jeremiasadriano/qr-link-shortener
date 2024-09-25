# Qr link shortener

A URL shortening service that allows users to generate short URLs and QR codes from long URLs.

## Features

- **Generate Short URL**: Allows users to provide a long URL and receive a shortened version.
- **Redirection**: The service automatically redirects to the original URL when the shortened link is accessed.
- **QR Code Generation**: Creates a QR code for the shortened URL.
- **URL Expiration**: Shortened URLs expire after 10 minutes.

## Endpoints

- **POST /url**: Shortens a long URL.
    - Request Body: `{ "url": "https://example.com/dadwaddwda" }`
- **GET /{shortUrl}**: Redirects to the original URL.
- **GET /qr/{shortUrl}**: Generates a QR code for the shortened URL.

## Setup

1. Clone the repository.
    ```shell
    git clone https://github.com/jeremiasadriano/qr-link-shortener.git
    cd qr-link-shortener
    ```
2. Run the application
    ```shell
    mvn spring-boot:run
    ```
   
## Contributions

Contributions are welcome! Feel free to open an issue or submit a pull request.