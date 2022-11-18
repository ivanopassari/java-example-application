#!/bin/sh

PROFILES=${ACTIVE_PROFILES:-default}

XMS=${JVM_XMS:-128m}
XMX=${JVM_XMX:-1024m}

JVM_FLAGS="\
  -server\
  -Xms${XMS}\
  -Xmx${XMX}\
  -Dspring.config.additional-location=file:/opt/application/config/\
  "

exec java ${JVM_FLAGS} -Dspring.profiles.active=${PROFILES} org.springframework.boot.loader.JarLauncher