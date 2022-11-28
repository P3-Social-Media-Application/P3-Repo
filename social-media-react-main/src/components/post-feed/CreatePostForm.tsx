import * as React from 'react';
import TextField from '@mui/material/TextField';
import { Box, Button } from '@mui/material';
import { apiUpsertPost } from '../../remote/social-media-api/post.api';


export default function CreatePostForm() {

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    //const response = await apiUpsertPost(data);
    apiUpsertPost(data);
  }

  return (
    <React.Fragment>
      <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
      <TextField
        required
          id="filled-multiline-static"
          label="Thoughts You Would Like to Share?"
          fullWidth
        />
        <TextField
            id="postImage"
            name="postImage"
            label="Add an Image or Diagram?"
            fullWidth
            variant="standard"
        />
        <Button 
            type="submit"
            variant="contained"
            sx={{ mt: 3, ml: 1 }}
            color="warning"
        >
            Create Post
        </Button>
      </Box>
    </React.Fragment>
  );
}