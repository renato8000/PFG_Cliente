cmake_minimum_required(VERSION 3.4.1)
set (CMAKE_VERBOSE_MAKEFILE on)
set(EXECUTABLE_OUTPUT_PATH      "${CMAKE_CURRENT_SOURCE_DIR}/src/main/assets/engine/${ANDROID_ABI}")
add_library( nativeutil SHARED src/main/cpp/nativeutil.cpp )

add_library(gtb SHARED src/main/cpp/gtb/gtb-probe.c src/main/cpp/gtb/gtb-dec.c src/main/cpp/gtb/gtb-att.c
                        src/main/cpp/gtb/sysport/sysport.c src/main/cpp/gtb/compression/wrap.c
                        src/main/cpp/gtb/compression/huffman/hzip.c src/main/cpp/gtb/compression/lzma/LzmaEnc.c
                        src/main/cpp/gtb/compression/lzma/LzmaDec.c src/main/cpp/gtb/compression/lzma/Alloc.c
                        src/main/cpp/gtb/compression/lzma/LzFind.c src/main/cpp/gtb/compression/lzma/Lzma86Enc.c
                        src/main/cpp/gtb/compression/lzma/Lzma86Dec.c src/main/cpp/gtb/compression/lzma/Bra86.c
                        src/main/cpp/gtb/compression/zlib/zcompress.c src/main/cpp/gtb/compression/zlib/uncompr.c
                        src/main/cpp/gtb/compression/zlib/inflate.c src/main/cpp/gtb/compression/zlib/deflate.c
                        src/main/cpp/gtb/compression/zlib/adler32.c src/main/cpp/gtb/compression/zlib/crc32.c
                        src/main/cpp/gtb/compression/zlib/infback.c src/main/cpp/gtb/compression/zlib/inffast.c
                        src/main/cpp/gtb/compression/zlib/inftrees.c src/main/cpp/gtb/compression/zlib/trees.c
                        src/main/cpp/gtb/compression/zlib/zutil.c src/main/cpp/gtb/compression/liblzf/lzf_c.c
                        src/main/cpp/gtb/compression/liblzf/lzf_d.c src/main/cpp/gtb/GtbProbe.cpp )

target_include_directories(gtb PUBLIC src/main/cpp/gtb/sysport src/main/cpp/gtb/compression
                                      src/main/cpp/gtb/compression/liblzf src/main/cpp/gtb/compression/zlib
                                      src/main/cpp/gtb/compression/lzma src/main/cpp/gtb/compression/huffman )

target_compile_definitions(gtb PRIVATE Z_PREFIX TARGET_OS=android __ANDROID__ )

add_library(rtb SHARED src/main/cpp/rtb/bitBoard.cpp src/main/cpp/rtb/material.cpp src/main/cpp/rtb/moveGen.cpp
                       src/main/cpp/rtb/position.cpp src/main/cpp/rtb/rtb-probe.cpp
                       src/main/cpp/rtb/tbprobe.cpp src/main/cpp/rtb/RtbProbe.cpp )

target_compile_definitions(rtb PRIVATE TARGET_OS=android __ANDROID__ __STDC_INT64__ _GLIBCXX_USE_C99_STDINT_TR1 _GLIBCXX_HAS_GTHREADS _GLIBCXX__PTHREADS )


target_include_directories( rtb PUBLIC src/main/cpp/rtb )


add_executable( stockfish src/main/cpp/Stockfish/src/main.cpp )
target_sources( stockfish PUBLIC src/main/cpp/Stockfish/src/benchmark.cpp src/main/cpp/Stockfish/src/bitbase.cpp
                                                           src/main/cpp/Stockfish/src/bitboard.cpp src/main/cpp/Stockfish/src/endgame.cpp
                                                           src/main/cpp/Stockfish/src/evaluate.cpp
                                                           src/main/cpp/Stockfish/src/material.cpp src/main/cpp/Stockfish/src/misc.cpp
                                                           src/main/cpp/Stockfish/src/movegen.cpp src/main/cpp/Stockfish/src/movepick.cpp
                                                           src/main/cpp/Stockfish/src/pawns.cpp src/main/cpp/Stockfish/src/position.cpp
                                                           src/main/cpp/Stockfish/src/psqt.cpp src/main/cpp/Stockfish/src/search.cpp
                                                           src/main/cpp/Stockfish/src/thread.cpp src/main/cpp/Stockfish/src/timeman.cpp
                                                           src/main/cpp/Stockfish/src/tt.cpp src/main/cpp/Stockfish/src/uci.cpp
                                                           src/main/cpp/Stockfish/src/ucioption.cpp src/main/cpp/Stockfish/src/syzygy/tbprobe.cpp )
target_include_directories( stockfish PUBLIC src/main/cpp/Stockfish/src/ src/main/cpp/Stockfish/src/syzygy )