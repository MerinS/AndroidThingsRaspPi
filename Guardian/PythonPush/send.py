from urllib2 import *
import urllib
import json
import sys
import time 
import datetime


st = str(datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S'))
MY_API_KEY="AIzaSyBUk23indVtn51NRRPBEz7OAQpg9wp0oBI"

messageTitle = sys.argv[1]

data={
    "to" : "/topics/my_little_topic",
    "data" : {
      "Type" : "Help Needed",
      "UserID" : 123,
      "Time" : st,
      "Latitude" : 12.947588,
      "Longitude" : 77.675624
    }

}

dataAsJSON = json.dumps(data)

request = Request(
    "https://gcm-http.googleapis.com/gcm/send",
    dataAsJSON,
    { "Authorization" : "key="+MY_API_KEY,
      "Content-type" : "application/json"
    }
)

print urlopen(request).read()