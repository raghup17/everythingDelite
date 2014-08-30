

optiml:
	cd ${HYPER_HOME}/published/OptiML && \
	sbt "; project OptiML-apps; compile" && \
	cd ${HYPER_HOME}

df:
	cd ${DELITE_HOME} && \
	sbt "; project framework; publish-local" && \
	cd ${HYPER_HOME}

dr:
	cd ${DELITE_HOME} && \
	sbt "; project runtime; publish-local" && \
	cd ${HYPER_HOME}

lms:
	cd ${LMS_HOME} && \
	sbt publish-local && \
	cd ${HYPER_HOME}
