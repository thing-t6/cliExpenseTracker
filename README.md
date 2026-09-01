# Java CLI Expense Tracker

A terminal-based expense manager built in Java. It tracks income and expenses along with transaction dates, calculates total spending and net balance, and saves reports locally.

## Features
- Interactive CLI prompts with input validation
- Tracks income vs. expenses with exact dates using `LocalDate`
- Custom Java object model (`Expenses`, `Income`)
- Local file persistence using `FileWriter` and `BufferedReader`

## How to Run
```bash
javac src/*.java
java -cp src Main