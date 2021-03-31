# MakStat-Revised Backend

## Data Source and Flyway
- Tables are created automatically by Hibernate (from the defined entities) when the program launches.
- Flyway, hence, skips V1 (create_tables) migration scripts and only runs V2 (add_sample_data) scripts.
