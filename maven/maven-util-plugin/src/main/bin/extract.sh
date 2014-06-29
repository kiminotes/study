#!/bin/bash

####
# Author: gang.lvg@alibaba-inc.com
####

VERSION="5.1.6"
GROUP_ID="com.taobao.tddl"

ARTIFACT_IDS="tddl-common,tddl-config,tddl-config-diamond,tddl-parser,tddl-atom,tddl-group,tddl-repo-mysql,tddl-repo-hbase,tddl-repo-oceanbase,tddl-repo-demo,tddl-optimizer,tddl-executor,tddl-matrix,tddl-rule,tddl-monitor,tddl-sequence,tddl-repo-bdb,tddl-net,tddl-server"

IFS=","
for item in $ARTIFACT_IDS
do
    echo "extract $item"
    mvn net.kiminotes:maven-util-plugin:1.0:extract -DgroupId="${GROUP_ID}" -DartifactId="${item}" -Dversion="${VERSION}"
    if [ "x$?" = "x" ]; then
        echo "FAILE"
        exit 1
    fi
done
