FROM us-aks-docker-local.artifactory.platform.manulife.io/jh/aks/base/java/java-jdk17-newrelic-contrast:latest

COPY ./pacdetails-0.0.1-SNAPSHOT.jar ./pacdetails-0.0.1-SNAPSHOT.jar

ARG UID=1001
ARG GID=1001

RUN addgroup --gid "${GID}" user
RUN adduser --uid "${UID}" --ingroup user user --disabled-password

USER $UID

EXPOSE 8080

ENTRYPOINT ["java","-jar","pacdetails-0.0.1-SNAPSHOT.jar"]