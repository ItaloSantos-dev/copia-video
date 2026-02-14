from fastapi import FastAPI
from youtube_transcript_api import YouTubeTranscriptApi

app = FastAPI()

@app.get("/transcript/{video_id}")
def home(video_id: str):
    yt = YouTubeTranscriptApi()
    transcript = yt.fetch(video_id, languages=['pt'])
    texto = " ".join([item.text for item in transcript])
    
    return {"transcript": texto}