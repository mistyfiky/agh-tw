#!/usr/bin/env bash
for readers in $(seq 10 10 100); do
    for writers in $(seq 1 1 10); do
        java Main ${1} ${readers} ${writers} 3 3 0 0 2>/dev/null
    done
done
