# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-jqiaobln;protocol=https;branch=master"

#inherit update-rc.d

#INITSCRIPT_PACKAGES = "${PN}"

#INITSCRIPT_NAME = "scull.init"
#update-rc.d no more supports start/stop
#INITSCRIPT_PARAMS = "start" 

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "efbb0d9c38ece561eeee94b2fe1faa9da4cf185b"

S = "${WORKDIR}/git"



EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}


do_install () {
    MODULE_DIR=${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input
    install -d $MODULE_DIR
#    install -d ${D}${sysconfdir}/init.d
#    install -m 0755 ${WORKDIR}/scull.init ${D}${sysconfdir}/init.d/
    install -m 0644 ${S}/scull/scull.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input
}


#FILES:${PN} += "${sysconfdir}/init.d/scull.init"
#FILES:${PN} += "\
#${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input/scull.ko \
#"
RPROVIDES_${PN} += "kernel-module-${PN}-${KERNEL_VERSION}"

#MACHINE_EXTRA_RECOMMENDS += "kernel-module-scull"
#IMAGE_INSTALL += "kernel-module-scull"

KERNEL_MODULE_AUTOLOAD += "scull"