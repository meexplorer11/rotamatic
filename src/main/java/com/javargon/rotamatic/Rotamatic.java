package com.javargon.rotamatic;

import java.io.IOException;

import com.javargon.rotamatic.service.RotaService;

/**
 * 
 * @author vivek
 *
 */
public class Rotamatic 
{
    public static void main( String[] args ) throws IOException
    {
        RotaService.prepareRota();
    }
}
