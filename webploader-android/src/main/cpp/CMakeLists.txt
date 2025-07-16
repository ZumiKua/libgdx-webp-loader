# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.13)

# Declares and names the project.

project("webploader")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             webploader-native

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
        main.cpp)

set(lib_src_DIR ./libwebp-1.2.1)

set(lib_build_DIR ./libwebp-1.2.1/outputs)

add_subdirectory( # Specifies the directory of the CMakeLists.txt file.
        ${lib_src_DIR}

        # Specifies the directory for the build outputs.
        ${lib_build_DIR} EXCLUDE_FROM_ALL)

target_include_directories( webploader-native PRIVATE ${lib_src_DIR}/src/webp )

target_link_libraries(webploader-native webpdecoder)

target_compile_definitions(webploader-native PRIVATE JAVA_CLASS_NAME=${JAVA_CLASS_NAME})

if(ANDROID)
    target_link_options(webploader-native PRIVATE "-Wl,-z,max-page-size=16384")
    target_link_options(webploader-native PRIVATE "-Wl,-z,common-page-size=16384")
endif()

#NDK will handle jni.h for us.
if(NOT ANDROID)
    target_include_directories( webploader-native PRIVATE ${JNI_HEADER_DIR} )
    if(${CMAKE_SYSTEM_NAME} STREQUAL Windows)
        target_include_directories( webploader-native PRIVATE ${JNI_HEADER_DIR}/win32 )
    elseif(${CMAKE_SYSTEM_NAME} STREQUAL Linux)
        target_include_directories( webploader-native PRIVATE ${JNI_HEADER_DIR}/linux )
    elseif(${CMAKE_SYSTEM_NAME} STREQUAL Darwin)
        target_include_directories( webploader-native PRIVATE ${JNI_HEADER_DIR}/mac )
    endif()

    if(CMAKE_SIZEOF_VOID_P EQUAL 8 AND NOT(FORCE32) )
        set_target_properties(webploader-native PROPERTIES OUTPUT_NAME webploader-native64)
    endif()
endif()