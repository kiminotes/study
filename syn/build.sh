#!/bin/bash

gcc -g -o syn main.c
sudo chown root:root syn
sudo chmod u+s syn
