#!/bin/bash

if [ $# -ne 5 ]; then
  echo 1>&2 "Usage: . $0 hostname realm username clientid"
  echo 1>&2 "  options:"
  echo 1>&2 "    hostname: localhost:8081"
  echo 1>&2 "    realm:keycloak-demo"
  echo 1>&2 "    clientid:demo"
  echo 1>&2 "    For verify ssl: use 'y' (otherwise it will send curl post with --insecure)"
  
  return
fi

HOSTNAME=$1
REALM_NAME=$2
USERNAME=$3
CLIENT_ID=$4
SECURE=$5



KEYCLOAK_URL=https://$HOSTNAME/auth/realms/$REALM_NAME/protocol/openid-connect/token



echo "Using Keycloak: $KEYCLOAK_URL"
echo "realm: $REALM_NAME"
echo "client-id: $CLIENT_ID"
echo "username: $USERNAME"
echo "secure: $SECURE"


if [[ $SECURE = 'y' ]]; then
	INSECURE=
else 
	INSECURE=--insecure
fi


echo -n Password: 
read -s PASSWORD


export TOKEN=$(curl -X POST "$KEYCLOAK_URL" "$INSECURE" \
 -H "Content-Type: application/x-www-form-urlencoded" \
 -d "username=$USERNAME" \
 -d "password=$PASSWORD" \
 -d 'grant_type=password' \
 -d "client_id=$CLIENT_ID" )

# -d "client_id=$CLIENT_ID" | jq -r '.access_token')

echo $TOKEN

if [[ $(echo $TOKEN) != 'null' ]]; then
	export KEYCLOAK_TOKEN=$TOKEN
fi

# https://github.com/akoserwal/keycloak-integrations/blob/master/curl-post-request/keycloak-curl.sh
# https://medium.com/keycloak/keycloak-jwt-token-using-curl-post-72c9e791ba8c

# ./keycloak-get-token.sh keycloak.ci.ultrasist.net:443 SpringBootKeycloak user1 login-app

# curl --location --request POST 'http://192.168.100.4:8081/sample' \
# --header 'Content-Type: application/json' --header 'Authorization: Bearer eyJhbG...rkzeXdE2YUw' \
# --data-raw '{ \
# "firstName":"xxx",
# "lastName":"yyy"
# }'

