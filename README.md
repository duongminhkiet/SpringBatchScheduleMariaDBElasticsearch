# This is a Spring API
# Database: MariaDB
# Search Engine: Elasticsearch
# Features: Auto Migrate data from MariaDB to Elasticsearch 

YOU CAN READ MORE AT : src/main/resources/settings/old

# Pre-required: 
# 1. You have installed and run a Elasticsearch on Your server with version <= 6.8.X, In this example I'm using ES version-6.8.4
# 2. Setting on Elasticsearch Server 
<img src="/docs/es_setting_server.png" alt="RUN"/>
<br/>

# 3. Elasticsearch Server already run
<img src="/docs/es_server_run.png" alt="RUN"/>
<br/>

# 4. Check version of Elasticsearch on Centos server 
<img src="/docs/es_server_version.png" alt="RUN"/>
<br/>

# 5. Check Elasticsearch on Chome Client
<img src="/docs/es_client1.png" alt="RUN"/>
<br/>
<img src="/docs/es_client2.png" alt="RUN"/>
<br/>

# 6. Mariadb Configuration
+ Create a database with name: springbatch
+ Create a table 'user (id, name)' with utf8mb4_unicode_ci format
+ Insert some data into 'user' table

<img src="/docs/mariadb_user_table_data.png" alt="RUN"/>
<br/>

# 7. Run Spring project 
<img src="/docs/spring_run.png" alt="RUN"/>
<br/>

# 8. Check Data store in elasticsearch 
<img src="/docs/es_client_check_data.png" alt="RUN"/>
<br/>

# 9. Search data in elasticsearch
<img src="/docs/search_data1.png" alt="RUN"/>
<br/>

# 10. Check auto insert from MariaDB into ES 
+ Insert 'Lê Nguyễn Phan Trần Phạm' into Mariadb => auto show on ES in a some seconds
<img src="/docs/new_es_inserted.png" alt="RUN"/>
<br/>



# SOME INF ABOUT ES 
# A. Manual Put a Setting To ES
```
{
    "settings": {
            "index": {
                "number_of_shards": "1",
                "analysis": {
                    "filter": {
                        "edge_ngram": {
                            "min_gram": "1",
                            "side": "front",
                            "type": "edge_ngram",
                            "max_gram": "30"
                        },
                        "folding-preserve": {
                            "type": "asciifolding",
                            "preserveOriginal": "true"
                        }
                    },
                    "normalizer": {
                        "lowercase-normalizer": {
                            "filter": [
                                "lowercase"
                            ],
                            "type": "custom"
                        }
                    },
                    "analyzer": {
                        "ngram-analyzer": {
                            "filter": [
                                "lowercase",
                                "edge_ngram"
                            ],
                            "type": "custom",
                            "tokenizer": "ngram_tokenizer"
                        }
                    },
                    "tokenizer": {
                        "ngram_tokenizer": {
                            "token_chars": [
                                "digit",
                                "letter",
                                "whitespace",
                                "symbol",
                                "punctuation"
                            ],
                            "min_gram": "1",
                            "type": "edge_ngram",
                            "max_gram": "30"
                        }
                    }
                },
                "number_of_replicas": "1"
            }
        },
    "mappings": {
            "properties" : {
                "title" : {
                   "type": "text",
                   "analyzer": "ngram-analyzer"
                },
                "content" : {
                    "type" : "text",
                    "analyzer" : "ngram-analyzer"
                }
            }   
    }
}
```
<img src="/docs/put_setting_es.png" alt="RUN"/>
<br/>

<img src="/docs/es_cf1.png" alt="RUN"/>
<br/>
<img src="/docs/es_cf2.png" alt="RUN"/>
<br/>
<img src="/docs/es_cf3.png" alt="RUN"/>
<br/>
<img src="/docs/es_cf4.png" alt="RUN"/>
<br/>
<img src="/docs/es_cf5.png" alt="RUN"/>
<br/>
