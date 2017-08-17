FROM mongo:3.4
#RUN bash -c 'touch run.sh'
#RUN bash -c 'touch dbscripts.js'

RUN mkdir -p /platform

ADD load-data.sh /platform
ADD init-scripts.js /platform

RUN chmod 600 /platform/load-data.sh
RUN chmod 600 /platform/init-scripts.js

RUN bash -c 'touch /platform/load-data.sh'
RUN bash -c 'touch /platform/init-scripts.js'

VOLUME $HOME/Documents/mongodb/data /data/db
EXPOSE 27017
ENTRYPOINT [ "mongod" ]
