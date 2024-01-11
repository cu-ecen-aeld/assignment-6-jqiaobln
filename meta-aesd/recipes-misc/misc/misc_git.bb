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

SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-jqiaobln;protocol=https;branch=master \
           file://faulty_load.init"

inherit update-rc.d

#INITSCRIPT_PACKAGES = "${PN}"

INITSCRIPT_NAME = "faulty_load.init"
#INITSCRIPT_PARAMS = "start"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "1892582341527d07119e5c85f98d8a90d70708ea"

S = "${WORKDIR}/git"


FILES:${PN} += "${sysconfdir}/init.d/faulty_load.init"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"


do_install () {
    MODULE_DIR=${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input
    install -d $MODULE_DIR
    install -m 0644 ${S}/misc-modules/hello.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input
    install -m 0644 ${S}/misc-modules/faulty.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/misc-modules/faulty_load.init ${D}${sysconfdir}/init.d/
}

#MACHINE_EXTRA_RECOMMENDS += "kernel-module-misc"

KERNEL_MODULE_AUTOLOAD += "faulty"
KERNEL_MODULE_AUTOLOAD += "hello"