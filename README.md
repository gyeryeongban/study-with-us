# Study With Us

A study-matching platform for creating and managing study groups — covering free/paid study registration, member approval, a community board, calendar, mentor applications, and payments. Built as a team project during **BIT Academy**.

> **Note:** This README reflects the project state during 2021.08.19–2021.10.19, the period I was on the team — not the team's final, completed result.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Team](#team)
- [Retrospective](#retrospective)

## Overview

The planned scope (see [use case diagram](./uml/UseCase_Final.pdf)) covered the full service: signup/login, free & paid study browsing/registration, a community board, calendar, mentor applications, payments, and admin tools.

## Features

### My Contributions

- **Member**: SNS signup/login, logout, profile photo upload, `MemberDao` implementation
- **Free Study**: registration, detail view, join request/cancellation, member approval/rejection
- **Database**: `MariadbMemberDao`, contributed to ERD design (`model.exerd`)

## Tech Stack

| Category | Details |
|---|---|
| Language | Java |
| Build | Gradle |
| Database | MariaDB (direct JDBC) |
| Communication | Custom socket-based client-server protocol |
| Libraries | Gson (JSON serialization), Guava |
| Test | JUnit |

## Architecture

Built without a framework like Spring — the client and server exchange requests/responses over raw **TCP sockets**.

```
app-client  ─── Socket (TCP) ───  app-server
 (RequestAgent, Net*Dao)           (RequestProcessor → DataProcessor → Mariadb*Dao)
```

- `app-client`: wraps user actions into a `Request` object and sends it via `RequestAgent` (`Net*Dao`)
- `app-server`: `RequestProcessor` receives the request and delegates to `DataProcessor`; `Mariadb*Dao` implementations handle the actual DB access and return a `Response`

## Getting Started

### Prerequisites

- JDK 8+
- MariaDB (or MySQL-compatible) running locally on port `3306`

### Installation

1. Clone the repository

   ```bash
   git clone https://github.com/gyeryeongban/study-with-us.git
   cd study-with-us/project/study-with-us-project
   ```

2. Build the project

   ```bash
   ./gradlew build
   ```

### Configuration

Create the database and user matching the hardcoded connection string used in this snapshot (`jdbc:mysql://localhost:3306/team3db`):

```sql
CREATE DATABASE team3db;
CREATE USER 'team3'@'localhost' IDENTIFIED BY '1111';
GRANT ALL PRIVILEGES ON team3db.* TO 'team3'@'localhost';
FLUSH PRIVILEGES;
```

Load the schema (and sample data, if needed):

```bash
mysql -u team3 -p team3db < app-client/docs/dbmodel/ddl.sql
mysql -u team3 -p team3db < app-client/docs/dbmodel/data.sql
```

> **Note:** The DB credentials above are hardcoded directly in the source (`ClientApp*.java`) rather than externalized — a known limitation of this early-stage snapshot.

### Running the Application

Start the server:

```bash
./gradlew :app-server:run
```

The server listens on TCP port `8888` (`ServerSocket(8888)`).

In a separate terminal, start the client:

```bash
./gradlew :app-client:run
```

The client connects to `127.0.0.1:8888` by default (see `RequestAgent`).

## Project Structure

```
project/study-with-us-project/
├── app-client/   # client (Net*Dao, ClientApp, RequestAgent)
└── app-server/   # server (Mariadb*Dao, ServerApp, RequestProcessor)
minutes/          # team meeting notes
uml/              # use case diagrams, ERD source files
```

## Team

| Name | Role | GitHub |
|---|---|---|
| Jei Kim | Community board (post CRUD, search, membership withdrawal) | [@Jei-Kim](https://github.com/Jei-Kim) |
| Joochang Kim | Auth & Member (signup, login, member/study domain models) | [@kimjoochang](https://github.com/kimjoochang) |
| Gyeryeong Ban | Free Study CRUD (browse, create, edit, delete, join/cancel, member approval) | [@gyeryeongban](https://github.com/gyeryeongban) |
| Seonyoung Ha | Paid Study CRUD (browse, create, edit, delete) | [@SeonyoungHa](https://github.com/SeonyoungHa) |

## Retrospective

My main takeaway was designing a client-server system from scratch over raw sockets — implementing the request/response protocol and DAO layer by hand instead of relying on a framework like Spring made the underlying mechanics much more concrete.
