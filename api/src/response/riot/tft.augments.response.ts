export interface TftAugmentsResponse {
    data: Map<string, TftAugmentBody>
}

export interface TftAugmentBody {
    name: string,
    image: TftAugmentBodyImage
}

export interface TftAugmentBodyImage {
    full: string,
    sprite: string,
}