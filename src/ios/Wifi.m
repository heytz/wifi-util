/********* Wifi.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import <SystemConfiguration/CaptiveNetwork.h>

@interface Wifi : CDVPlugin {
  // Member variables go here.
}

- (void)getCurrentSSID:(CDVInvokedUrlCommand*)command;
@end

@implementation Wifi

- (void)getCurrentSSID:(CDVInvokedUrlCommand*)command
{

    CDVPluginResult *pluginResult = nil;
    NSString *ssid = nil;
    NSArray *ifs = (__bridge   id)CNCopySupportedInterfaces();
    NSLog(@"ifs:%@",ifs);
    for (NSString *ifnam in ifs) {
         NSDictionary *info = (__bridge id)CNCopyCurrentNetworkInfo((__bridge CFStringRef)ifnam);
         NSLog(@"dici：%@",[info  allKeys]);
         if (info[@"SSID"]) {
               ssid = info[@"SSID"];
         }
     }

     if(ssid!=nil && [ssid length] > 0){
         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:ssid];
     } else {
         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
     }
     [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
