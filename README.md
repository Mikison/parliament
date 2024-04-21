# Polish Parliament Data Interface

## Overview

This project is designed to interact with the official Polish Parliament API to collect and store data about parliamentary activities in a local database. It uses a modular architecture to separate concerns such as data retrieval, data storage, web API endpoints, logging, and data updating. The frontend is built with Angular, providing a simple user interface to interact with the data.

## Modules

- **parliament-client**: Handles connections to the Polish Parliament API and retrieves data.
- **parliament-data**: Contains all data repositories and model definitions.
- **parliament-logging**: Manages application logging.
- **parliament-updater**: Allows for on-demand updates by instructing the parliament-client to fetch new data.
- **parliament-webapi**: Provides endpoints through which users can access data.

## Features

- Retrieve and store official parliamentary data.
- Update data on demand.
- Access data through a well-defined API.
- Simple Angular frontend to display data.

## Tech Stack

- **Backend**: Spring Boot, Spring Data JPA
- **Frontend**: Angular, TypeScript
- **Database**: PostgreSQL

## Getting Started

### Prerequisites

- JDK 11 or higher
- Node.js and npm (for Angular)
- PostgreSQL

### Installation and Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/Mikison/parliament.git
   cd parliament
   ```
2. Run locally (Angular is injected in Spring Boot)
