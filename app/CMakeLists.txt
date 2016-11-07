# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_VERBOSE_MAKEFILE on)
set(EXECUTABLE_OUTPUT_PATH      "${CMAKE_CURRENT_SOURCE_DIR}/src/main/assets/${ANDROID_ABI}")
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             nativeutil

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             src/main/cpp/nativeutil.cpp )

add_library (gtb SHARED 	src/main/cpp/gtb/gtb-probe.c src/main/cpp/gtb/gtb-dec.c src/main/cpp/gtb/gtb-att.c src/main/cpp/gtb/sysport/sysport.c src/main/cpp/gtb/compression/wrap.c
                            src/main/cpp/gtb/compression/huffman/hzip.c src/main/cpp/gtb/compression/lzma/LzmaEnc.c src/main/cpp/gtb/compression/lzma/LzmaDec.c
                        	src/main/cpp/gtb/compression/lzma/Alloc.c src/main/cpp/gtb/compression/lzma/LzFind.c src/main/cpp/gtb/compression/lzma/Lzma86Enc.c
                        	src/main/cpp/gtb/compression/lzma/Lzma86Dec.c src/main/cpp/gtb/compression/lzma/Bra86.c src/main/cpp/gtb/compression/zlib/zcompress.c
                        	src/main/cpp/gtb/compression/zlib/uncompr.c src/main/cpp/gtb/compression/zlib/inflate.c src/main/cpp/gtb/compression/zlib/deflate.c
                        	src/main/cpp/gtb/compression/zlib/adler32.c src/main/cpp/gtb/compression/zlib/crc32.c src/main/cpp/gtb/compression/zlib/infback.c
                        	src/main/cpp/gtb/compression/zlib/inffast.c src/main/cpp/gtb/compression/zlib/inftrees.c src/main/cpp/gtb/compression/zlib/trees.c
                        	src/main/cpp/gtb/compression/zlib/zutil.c src/main/cpp/gtb/compression/liblzf/lzf_c.c src/main/cpp/gtb/compression/liblzf/lzf_d.c
                        	src/main/cpp/gtb/GtbProbe.cpp)

target_include_directories (gtb PUBLIC src/main/cpp/gtb/sysport src/main/cpp/gtb/compression src/main/cpp/gtb/compression/liblzf src/main/cpp/gtb/compression/zlib
                                src/main/cpp/gtb/compression/lzma src/main/cpp/gtb/compression/huffman)
target_compile_definitions(gtb PRIVATE Z_PREFIX TARGET_OS=android __ANDROID__)
add_library (rtb SHARED src/main/cpp/rtb/bitBoard.cpp src/main/cpp/rtb/material.cpp src/main/cpp/rtb/moveGen.cpp src/main/cpp/rtb/position.cpp src/main/cpp/rtb/rtb-probe.cpp
                        src/main/cpp/rtb/tbprobe.cpp src/main/cpp/rtb/RtbProbe.cpp)

add_executable (stockfish src/main/cpp/stockfish/benchmark.cpp src/main/cpp/stockfish/main.cpp src/main/cpp/stockfish/movegen.cpp src/main/cpp/stockfish/pawns.cpp
                        src/main/cpp/stockfish/thread.cpp src/main/cpp/stockfish/uci.cpp src/main/cpp/stockfish/psqt.cpp src/main/cpp/stockfish/bitbase.cpp
                        src/main/cpp/stockfish/endgame.cpp src/main/cpp/stockfish/material.cpp src/main/cpp/stockfish/movepick.cpp src/main/cpp/stockfish/position.cpp
                        src/main/cpp/stockfish/timeman.cpp src/main/cpp/stockfish/ucioption.cpp src/main/cpp/stockfish/bitboard.cpp src/main/cpp/stockfish/evaluate.cpp
                        src/main/cpp/stockfish/misc.cpp src/main/cpp/stockfish/search.cpp src/main/cpp/stockfish/tt.cpp src/main/cpp/stockfish/syzygy/tbprobe.cpp )

target_include_directories (stockfish PUBLIC src/main/cpp/stockfish src/main/cpp/stockfish/syzygy)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in the
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       nativeutil

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
