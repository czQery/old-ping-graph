Ping graph
============

One of my first ever coding projects! (created in early 2020)

This tool pings every second ip you set, then saves it to json, and creates a graph after 24 hours.

**Commands**

You can even use these commands to create hourly or daily graphs manually:

```
graph day
graph hour
stop
```
**API**

Or you can use API and get raw json data:

```http
https://localhost:8080/ping/date?token=yourtoken&date=19.09.2021
https://localhost:8080/ping/time?token=yourtoken&date=19.09.2021&time=10:00:00
```


**Config.json**

```json
{
  "api_name":"ping",
  "port":8080,
  "token":"yourtoken",
  "address":"google.com",
  "maxping":200,
  "graph_repository":"Graph/"
}
```
