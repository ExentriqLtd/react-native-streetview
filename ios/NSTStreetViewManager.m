//
//  NSTStreetViewManager.m
//  react-native-streetview
//
//  Created by Amit Palomo on 26/04/2017.
//  Copyright © 2017 Nester.co.il All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTViewManager.h>
#import <React/RCTConvert+CoreLocation.h>

@import GoogleMaps;

@interface RNTGMSPanoramaView : GMSPanoramaView
    @property (nonatomic, copy) RCTBubblingEventBlock onChange;
@end

@interface NSTStreetViewManager : RCTViewManager<GMSPanoramaViewDelegate>
@end


@implementation RNTGMSPanoramaView

@end

@implementation NSTStreetViewManager

RCT_EXPORT_MODULE()

RCT_CUSTOM_VIEW_PROPERTY(coordinate, CLLocationCoordinate, GMSPanoramaView) {
    if (json == nil) return;
    
    [view moveNearCoordinate:[RCTConvert CLLocationCoordinate2D:json]];
}

- (void)panoramaView:(RNTGMSPanoramaView *)view didMoveToPanorama:(GMSPanorama *)panorama nearCoordinate:(CLLocationCoordinate2D)coordinate {
    NSLog(@"onMove");
    view.onChange(@{@"invalid": @(FALSE)});
}
- (void)panoramaView:(RNTGMSPanoramaView *)view error:(NSError *)error onMoveNearCoordinate:(CLLocationCoordinate2D)coordinate {
    NSLog(@"onMove error");
    view.onChange(@{@"invalid": @(TRUE)});
}

RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)

RCT_EXPORT_VIEW_PROPERTY(allGesturesEnabled, BOOL)

- (UIView *)view {
    RNTGMSPanoramaView *panoView = [[RNTGMSPanoramaView alloc] initWithFrame:CGRectZero];
    panoView.delegate = self;
    return panoView;
}

@end
