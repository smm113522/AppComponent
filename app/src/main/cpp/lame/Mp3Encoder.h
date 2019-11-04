//
// Created by mac on 2019-11-04.
//

#ifndef LAMEDEMO2C_MP3ENCODER_H
#define LAMEDEMO2C_MP3ENCODER_H

#endif //LAMEDEMO2C_MP3ENCODER_H

#include <cstdio>
#include "lame.h"

class Mp3Encoder {
private:
    FILE* pcmFile;
    FILE* mp3File;
    lame_t lameClient;
public:
    Mp3Encoder();
    ~Mp3Encoder();
    int Init(const char* pcmFilePath,int channels,int bitRate,int sampleRate,const char* mp3FilePath);
    void Encode();
    void Destory();
};